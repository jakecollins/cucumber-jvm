package cucumber.runtime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EnvTest {

    private Env env = new Env("env-test");

    @Test
    public void looks_up_value_from_environment() {
        assertNotNull(env.get("PATH"));
    }

    @Test
    public void returns_null_for_absent_key() {
        assertNull(env.get("pxfj54#"));
    }

    @Test
    public void looks_up_value_from_system_properties() {
        try {
            System.setProperty("env.test", "from-props");
            assertEquals("from-props", env.get("env.test"));
            assertEquals("from-props", env.get("ENV_TEST"));
        } finally {
            System.getProperties().remove("env.test");
        }
    }

    @Test
    public void looks_up_value_from_resource_bundle() {
        assertEquals("from-bundle", env.get("env.test"));
        assertEquals("from-bundle", env.get("ENV_TEST"));
    }
}
