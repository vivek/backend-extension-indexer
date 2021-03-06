package org.jenkinsci.extension_indexer;

import net.sf.json.JSONObject;
import org.jenkinsci.extension_indexer.ExtensionPointListGenerator.Family;
import org.jvnet.hudson.update_center.MavenArtifact;

import java.util.Map;

/**
 * Captures key details of {@link Extension} but without keeping much of the work in memory.
 *
 * @author Kohsuke Kawaguchi
 * @see Extension
 */
public class ExtensionSummary {
    /**
     * Back reference to the artifact where this implementation was found.
     */
    public final MavenArtifact artifact;

    public final String extensionPoint;

    public final String implementation;

    public final String confluenceDoc;

    public final JSONObject json;

    public final boolean hasView;

    public final Map<String,String> views;

    /**
     * True for a definition of extension point, false for an implementation of extension point.
     */
    public final boolean isDefinition;

    /**
     * Family that this extension belongs to. Eithe {@link Family#definition} is 'this'
     * or {@link Family#implementations} includes 'this'
     */
    public final Family family;

    public ExtensionSummary(Family f, Extension e) {
        this.family = f;
        this.isDefinition = e.isDefinition();
        this.artifact = e.artifact;
        this.extensionPoint = e.extensionPoint.getQualifiedName().toString();
        this.implementation = e.implementation!=null ? e.implementation.getQualifiedName().toString() : null;
        this.confluenceDoc = e.getConfluenceDoc();
        this.hasView = e.hasView();
        this.views = e.views;
        this.json = e.toJSON();
    }
}
