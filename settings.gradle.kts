rootProject.name = "Carpet LMS Addition"

pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

listOf(
    "1_21_10",
    "1_21_11",
    "26_1",
).forEach {
    include("mc$it")
    project(":mc$it").projectDir = file("versions/$it")
}
