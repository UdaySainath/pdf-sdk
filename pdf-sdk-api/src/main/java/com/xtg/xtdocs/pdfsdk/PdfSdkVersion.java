/*
 * Copyright 2024 XTG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xtg.xtdocs.pdfsdk;

/**
 * Reports the running PDF SDK version.
 *
 * <p>The value is read from the jar manifest ({@code Implementation-Version}),
 * so it always matches the artifact you actually deployed. When running from
 * an IDE or an exploded classpath where no manifest is present, it returns
 * {@code "development"}.
 *
 * @since 0.1.0
 */
public final class PdfSdkVersion {

    private static final String VERSION = resolve();

    private PdfSdkVersion() {
        throw new AssertionError("no instances");
    }

    /**
     * Returns the SDK version.
     *
     * @return the version string, or {@code "development"} if unknown
     */
    public static String get() {
        return VERSION;
    }

    private static String resolve() {
        Package pkg = PdfSdkVersion.class.getPackage();
        if (pkg != null) {
            String v = pkg.getImplementationVersion();
            if (v != null && !v.trim().isEmpty()) {
                return v;
            }
        }
        return "development";
    }
}