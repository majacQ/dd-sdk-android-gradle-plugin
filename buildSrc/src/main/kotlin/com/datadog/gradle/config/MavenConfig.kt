/*
 * Unless explicitly stated otherwise all files in this repository are licensed under the Apache License Version 2.0.
 * This product includes software developed at Datadog (https://www.datadoghq.com/).
 * Copyright 2020-Present Datadog, Inc.
 */

package com.datadog.gradle.config

import com.datadog.gradle.utils.Version
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.named
import org.gradle.plugins.signing.SigningExtension
import java.net.URI

object MavenConfig {

    val VERSION = Version(1, 13, 1, Version.Type.Release)
    const val GROUP_ID = "com.datadoghq"
    const val PUBLICATION = "pluginMaven"
}

@Suppress("UnstableApiUsage")
fun Project.publishingConfig(projectDescription: String) {
    val projectName = name
    val signingExtension = extensions.findByType(SigningExtension::class)

    if (signingExtension == null) {
        System.err.println("Missing signing extension for $projectName")
        return
    }
    signingExtension.apply {
        val privateKey = System.getenv("GPG_PRIVATE_KEY")
        val password = System.getenv("GPG_PASSWORD")
        isRequired = System.getenv("CI").toBoolean() && !hasProperty("dd-skip-signing")
        useInMemoryPgpKeys(privateKey, password)
        // com.gradle.plugin-publish plugin will automatically add signing task "signPluginMavenPublication"
        // sign(publishingExtension.publications.getByName(MavenConfig.PUBLICATION))
    }

    afterEvaluate {
        tasks.named("javadocJar", Jar::class.java).configure {
            group = "publishing"
            dependsOn("dokkaJavadoc")
            archiveClassifier.convention("javadoc")
            from("${buildDir.canonicalPath}/reports/javadoc")
        }

        val publishingExtension = extensions.findByType(PublishingExtension::class)
        if (publishingExtension == null) {
            System.err.println("Missing publishing extension for $projectName")
            return@afterEvaluate
        }

        publishingExtension.apply {
            repositories.maven {
                url = URI("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                val username = System.getenv("OSSRH_USERNAME")
                val password = System.getenv("OSSRH_PASSWORD")
                if ((!username.isNullOrEmpty()) && (!password.isNullOrEmpty())) {
                    credentials(PasswordCredentials::class.java) {
                        setUsername(username)
                        setPassword(password)
                    }
                } else {
                    System.err.println("Missing publishing credentials for $projectName")
                }
            }

            publications.getByName(MavenConfig.PUBLICATION) {
                check(this is MavenPublication)

                groupId = MavenConfig.GROUP_ID
                artifactId = projectName
                version = MavenConfig.VERSION.name

                pom {
                    name.set(projectName)
                    description.set(projectDescription)
                    url.set("https://github.com/DataDog/dd-sdk-android-gradle-plugin/")

                    licenses {
                        license {
                            name.set("Apache-2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0")
                        }
                    }
                    organization {
                        name.set("Datadog")
                        url.set("https://www.datadoghq.com/")
                    }
                    developers {
                        developer {
                            name.set("Datadog")
                            email.set("info@datadoghq.com")
                            organization.set("Datadog")
                            organizationUrl.set("https://www.datadoghq.com/")
                        }
                    }

                    scm {
                        url.set("https://github.com/DataDog/dd-sdk-android-gradle-plugin/")
                        connection.set(
                            "scm:git:git@github.com:Datadog/dd-sdk-android-gradle-plugin.git"
                        )
                        developerConnection.set(
                            "scm:git:git@github.com:Datadog/dd-sdk-android-gradle-plugin.git"
                        )
                    }
                }
            }
        }
    }
}
