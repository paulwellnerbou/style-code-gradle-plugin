package de.wellerbou.gradle.stylecode

import de.wellnerbou.stylecode.StyleCodeBuilder
import org.gradle.api.Plugin
import org.gradle.api.Project

class StyleCodePlugin implements Plugin<Project> {
    void apply(Project project) {
        project.extensions.create("stylecode", StyleCodePluginExtension)
        project.task('styleCode') << {
            println "Running style-code for ${project.stylecode.markdownFile}..."
            new StyleCodeBuilder(project.stylecode.markdownFile)
                    .useStylesAndScriptsFrom(project.stylecode.urlToFetchResourcesFrom)
                    .excludeResourcesMatching(project.stylecode.excludePatterns)
                    .includeInlineScripts(project.stylecode.includeInlineScripts)
                    .withIframeHtmlTemplate(project.stylecode.iframeTemplate)
                    .withIndexHtmlTemplate(project.stylecode.indexTemplate)
                    .build().generate(project.stylecode.outputDirectory);
        }
    }
}

class StyleCodePluginExtension {
    String markdownFile;
    String urlToFetchResourcesFrom;
    boolean includeInlineScripts = false;
    Iterable<String> excludePatterns;
    Iterable<String> additionalResources;
    String indexTemplate;
    String iframeTemplate;
    String outputDirectory;
}
