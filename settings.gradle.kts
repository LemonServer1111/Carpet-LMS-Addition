rootProject.name = "Carpet LMS Addition"

pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        mavenCentral()
        gradlePluginPortal()
    }
}

include("versions:1.21.10")
project(":versions:1.21.10").projectDir = file("versions/1.21.10")
