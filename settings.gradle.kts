rootProject.name = "Carpet LMS Addition"

pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        mavenCentral()
        gradlePluginPortal()
    }
}

listOf(
    "1_21_10",
    "1_21_11",
    "26_1",
).forEach {
    include("mc$it")
    project(":mc$it").projectDir = file("versions/$it")
}
