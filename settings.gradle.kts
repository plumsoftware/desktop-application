pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
    }
}

rootProject.name = "testing-reaction-of-vehicle-driver-desktop-application"

include(":offline-application-hybrid")
include(":ethernet-application-reaction-test")
include(":ethernet-application-admin")