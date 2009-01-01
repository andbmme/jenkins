package hudson.model;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import hudson.tasks.Builder;
import hudson.tasks.Shell;
import org.jvnet.hudson.test.HudsonTestCase;

import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
public class FreeStyleProjectTest extends HudsonTestCase {
    /**
     * Tests a trivial configuration round-trip.
     *
     * The goal is to catch a P1-level issue that prevents all the form submissions to fail.
     */
    public void testConfigSubmission() throws Exception {
        FreeStyleProject project = createFreeStyleProject();
        Shell shell = new Shell("echo hello");
        project.getBuildersList().add(shell);

        // emulate the user behavior
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage(project,"configure");

        HtmlForm form = page.getFormByName("config");
        submit(form);

        List<Builder> builders = project.getBuilders();
        assertEquals(1,builders.size());
        assertEquals(Shell.class,builders.get(0).getClass());
        assertEquals("echo hello",((Shell)builders.get(0)).getCommand());
        assertTrue(builders.get(0)!=shell);
    }
}
