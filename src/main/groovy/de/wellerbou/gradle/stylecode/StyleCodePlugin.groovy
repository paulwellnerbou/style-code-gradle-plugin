package de.wellerbou.gradle.stylecode

import de.wellnerbou.stylecode.StyleCodeBuilder
import org.gradle.api.Plugin
import org.gradle.api.Project

class StyleCodePlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create("stylecode", StyleCodePluginExtension)
        project.task('styleCode') << {
            println "Running style-code for ${project.stylecode.markdownFile}..."
            StyleCodeBuilder sb = new StyleCodeBuilder(project.stylecode.markdownFile);
            if(project.stylecode.useResourcesFrom != null) {
                sb.useStylesAndScriptsFrom(project.stylecode.useResourcesFrom)
            }
            if(project.stylecode.excludePatterns != null) {
                sb.excludeResourcesMatching(project.stylecode.excludePatterns);
            }
            if(project.stylecode.additionalResources != null) {
                sb.withAdditionalResources(project.stylecode.additionalResources);
            }
            sb.includeInlineScripts(project.stylecode.includeInlineScripts);
            if(project.stylecode.iframeTemplate != null) {
                sb.withIframeHtmlTemplate(project.stylecode.iframeTemplate)
            }
            if(project.stylecode.indexTemplate != null) {
                sb.withIndexHtmlTemplate(project.stylecode.indexTemplate)
            }
            sb.build().generate(project.stylecode.outputDirectory == null?project.buildDir.absolutePath+"/stylecode/":project.stylecode.outputDirectory);
        }
    }
}

class StyleCodePluginExtension {
    String markdownFile;
    String useResourcesFrom;
    boolean includeInlineScripts = false;
    Iterable<String> excludePatterns;
    Iterable<String> additionalResources;
    String indexTemplate;
    String iframeTemplate;
    String outputDirectory;
}
