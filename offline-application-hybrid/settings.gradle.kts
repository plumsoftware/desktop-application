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

rootProject.name = "offline-application-hybrid"

include(":authorization-ui-module")
include(":theme-ui-module")
include(":add-new-user-ui-module")
include(":all-users-ui-module")
include(":local-store-module")
