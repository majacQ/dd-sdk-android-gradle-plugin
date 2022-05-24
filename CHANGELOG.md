# 1.4.0 / 2022-05-17

- [IMPROVEMENT] Add shrinking options for the R8/Proguard/DexGuard mapping file (short aliases, indents trim) [#86](https://github.com/DataDog/dd-sdk-android-gradle-plugin/pull/86)
- [IMPROVEMENT] Documentation updates [#79](https://github.com/DataDog/dd-sdk-android-gradle-plugin/pull/79), [#84](https://github.com/DataDog/dd-sdk-android-gradle-plugin/pull/84)

# 1.3.0 / 2022-03-28

- [IMPROVEMENT] Use OkHttp 4.9.x in plugin dependency
- [IMPROVEMENT] Allow providing a custom path for the R8/Proguard/DexGuard mapping file (e.g.: `datadog { mappingFilePath = "path/to/mapping.txt" }`) [#71](https://github.com/DataDog/dd-sdk-android-gradle-plugin/issues/71) (Thanks [@pietrini](https://github.com/pietrini))

# 1.2.1 / 2021-12-10

- [BUGFIX] Fix dependency check task for project with multiple modules and different flavors

# 1.2.0 / 2021-12-06

- [IMPROVEMENT] Publish plugin on Gradle Portal
- [IMPROVEMENT] Add support to all Datadog regions
- [BUGFIX] Fix Git files path computation

# 1.1.0 / 2021-07-21

- [IMPROVEMENT] Prevent listing git ignored files
- [IMPROVEMENT] Allow customizing the remote repository url
- [IMPROVEMENT] Remove credentials (if any) from remote repository url
- [IMPROVEMENT] Support Gradle configuration cache
- [BUGFIX] Remove Gradle API from dependencies

# 1.0.1 / 2021-04-21

- [BUGFIX] Fix POM file missing dependencies

# 1.0.0 / 2021-03-22

- [FEATURE] Allow uploading Proguard/R8 mapping files for all variants with obfuscation enabled