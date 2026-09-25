package com.xtg.xtdocs.pdfsdk;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PdfSdkVersionTest {

    @Test
    void returns_non_null_non_empty_version() {
        String version = PdfSdkVersion.get();
        assertNotNull(version);
        assertFalse(version.trim().isEmpty());
    }

    @Test
    void falls_back_to_development_when_not_packaged() {
        // Running from target/classes (no manifest), so version is unknown.
        assertEquals("development", PdfSdkVersion.get());
    }
}