# Contributing to PDF SDK

## Prerequisites

- JDK 17 or later
- No local Maven required — use the wrapper

## Build

On macOS or Linux:

```bash
./mvnw clean verify
```

On Windows:

```powershell
.\mvnw.cmd clean verify
```

## Test

```bash
./mvnw test                    # unit tests
./mvnw verify                  # unit + integration tests
./mvnw -pl pdf-sdk-api test    # only one module
```

## Commit conventions

We use Conventional Commits: `<type>(<scope>): <subject>`

**Types:** `feat`, `fix`, `docs`, `refactor`, `test`, `build`, `ci`, `chore`

**Scopes:** `api`, `core`, `engine-pdfbox`, `bom`, `testkit`, `ci`, `docs`

Example:

```
feat(engine-pdfbox): support multi-frame TIFF

Adds frame iteration for TIFF inputs. Each frame becomes a page
in the output PDF.

Refs: S3-T03
```

## Task IDs

Every commit that advances a sprint task references it in the footer
(e.g. `Refs: S3-T03`). This lets us generate a release changelog from
`git log` alone.

## Pull requests

- One task per PR. Split larger tasks if needed.
- Fill in the PR template completely.
- Wait for CI green before requesting review.
- Squash-merge to `main`.

## Adding a dependency

Any new production dependency in any module requires an ADR in
`docs/adr/`. The bar is high: we ship an SDK, and every dependency
is one more thing our users inherit.

`pdf-sdk-api` may **never** have a production dependency.

## Architecture decisions

Significant design choices get an ADR. Copy `docs/adr/0000-template.md`,
number it as the next free integer, and open a PR.