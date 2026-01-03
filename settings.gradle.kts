import groovy.json.JsonSlurper

rootProject.name = "Carpet LMS Addition"

pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        maven {
            name = "Jitpack"
            url = uri("https://jitpack.io")
            @Suppress("UnstableApiUsage")
            content { includeGroupAndSubgroups("com.github") }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.replaymod.preprocess" -> {
                    useModule("com.github.Fallen-Breath:preprocessor:${requested.version}")
                }
            }
        }
    }
}

val settingsJson = file("settings.json").readText()
val settings = JsonSlurper().parseText(settingsJson) as Map<*, *>
val versions = settings["versions"] as List<*>
for (versionAny in versions) {
    val version = versionAny.toString()
    include(":$version")

    val proj = project(":$version")
    proj.projectDir = file("versions/$version")
    proj.buildFileName = "../../common.gradle.kts"
}
