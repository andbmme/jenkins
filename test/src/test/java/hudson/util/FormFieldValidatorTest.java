package hudson.util;

import hudson.model.FreeStyleProject;
import org.jvnet.hudson.test.Bug;
import org.jvnet.hudson.test.HudsonTestCase;
import org.jvnet.hudson.test.recipes.WithPlugin;

/**
 * @author Kohsuke Kawaguchi
 */
public class FormFieldValidatorTest extends HudsonTestCase {
    @Bug(2771)
    @WithPlugin("tasks.hpi")
    public void test2771() throws Exception {
        FreeStyleProject p = createFreeStyleProject();
        new WebClient().getPage(p,"configure");
    }
}
