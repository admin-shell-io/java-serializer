package io.adminshell.aas.v3.dataformat.xml;

import java.util.HashMap;
import java.util.Map;


public class AasXmlNamespaceContext {

    public static final String AAS_PREFERRED_PREFIX = "aas";
    public static final String AAS_URI = "http://www.admin-shell.io/aas/3/0";

    public static final String ABAC_PREFERRED_PREFIX = "abac";
    public static final String ABAC_URI = "http://www.admin-shell.io/aas/abac/3/0";

    public static final String COMMON_PREFERRED_PREFIX = "aas_common";
    public static final String COMMON_URI = "http://www.admin-shell.io/aas_common/3/0";

    public static final String IEC61360_PREFERRED_PREFIX = "IEC61360";
    public static final String IEC61360_URI = "http://www.admin-shell.io/IEC61360/3/0";

    public static final String XSI_PREFERRED_PREFIX = "xsi";
    public static final String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";

    public static final Map<String, String> PREFERRED_PREFIX_CONTEXT = new HashMap<>();

    static {
        PREFERRED_PREFIX_CONTEXT.put(AAS_PREFERRED_PREFIX, AAS_URI);
        PREFERRED_PREFIX_CONTEXT.put(ABAC_PREFERRED_PREFIX, ABAC_URI);
        PREFERRED_PREFIX_CONTEXT.put(COMMON_PREFERRED_PREFIX, COMMON_URI);
        PREFERRED_PREFIX_CONTEXT.put(IEC61360_PREFERRED_PREFIX, IEC61360_URI);
        PREFERRED_PREFIX_CONTEXT.put(XSI_PREFERRED_PREFIX, XSI_URI);
    }

    private AasXmlNamespaceContext() {
    }
}
