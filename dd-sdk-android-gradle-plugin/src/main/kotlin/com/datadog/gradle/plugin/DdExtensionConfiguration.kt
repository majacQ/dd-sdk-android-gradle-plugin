package com.datadog.gradle.plugin

/**
 * Base extension used to configure the `dd-android-gradle-plugin`.
 */
open class DdExtensionConfiguration(
    val name: String = ""
) {

    /**
     * The environment name for the application.
     */
    var environmentName: String? = null

    /**
     * The environment name for the application.
     * By default (null) it will read the version name of your application from your gradle
     * configuration.
     */
    var versionName: String? = null

    /**
     * The service name for the application.
     * By default (null) it will read the package name of your application from your gradle
     * configuration.
     */
    var serviceName: String? = null

    /**
     * The Datadog site to upload your data to (one of "US", "EU", "GOV").
     */
    var site: String? = null
}
