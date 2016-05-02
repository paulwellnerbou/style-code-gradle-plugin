package de.wellerbou.gradle.stylecode

import org.gradle.api.Plugin
import org.gradle.api.Project

class StyleCodePlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create("stylecode", StyleCodePluginExtension)
        project.task('styleCode') << {
            println "Running style-code for ${project.stylecode.markdownfile}..."
        }
    }
}

class StyleCodePluginExtension {
    String markdownfile
}
