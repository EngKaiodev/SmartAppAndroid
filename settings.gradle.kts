pluginManagement {
    repositories {
        // garante repositórios para plugins
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    // Mapeia plugins que não estão no Plugin Portal para seus artefatos Maven com versão.
    // Isso resolve plugins como "com.google.gms.google-services" quando aplicados sem versão no módulo.
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.google.gms.google-services" -> {
                    useModule("com.google.gms:google-services:4.3.15")
                }
                // Se no futuro usar outros plugins que não estejam no Gradle Plugin Portal,
                // adicione mapeamentos aqui.
            }
        }
    }
}

dependencyResolutionManagement {
    // modo recomendado: preferir repositórios declarados aqui
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SmartApp"
include(":app")
