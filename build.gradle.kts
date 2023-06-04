plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.1").apply(false)
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("android").version("1.8.20").apply(false)
    kotlin("multiplatform").version("1.8.20").apply(false)
    id("org.jetbrains.compose").version("1.4.0").apply(false)
    kotlin("plugin.serialization").version("1.8.0").apply(false)
    id("io.github.skeptick.libres").version("1.1.8").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
