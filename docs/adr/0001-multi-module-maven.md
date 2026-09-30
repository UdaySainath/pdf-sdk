# ADR-0001: Multi-module Maven build with a BOM

- **Status:** Accepted
- **Date:** 2024-01-01
- **Deciders:** XTG engineering

## Context

The PDF SDK ships multiple deployable artifacts (a public API module,
an orchestration core, one module per conversion engine, a test support
library, and later a Spring Boot starter and a CLI). Consumers need
to align versions across these artifacts without managing each one
individually.

We need to choose:

1. Build tool: Maven or Gradle.
2. Repository layout: monorepo or per-artifact repos.
3. Version alignment: consumers import a BOM or pick versions by hand.

## Decision

We will build with **Maven**, in a **single repository**, using a
**multi-module reactor**, and we will publish a **BOM** (`pdf-sdk-bom`)
as the recommended import point for consumers.

## Consequences

### Positive

- One `./mvnw clean verify` builds everything. CI is trivial.
- The BOM lets consumers omit versions for every PDF SDK artifact.
- Maven's reactor handles inter-module dependencies without extra config.
- Maven is the lingua franca for Java SDKs — reduces friction for adopters.
- Enforcer, dependency:analyze, and japicmp are Maven-native.

### Negative

- Maven XML is verbose compared to Gradle Kotlin DSL.
- Cross-module refactors require touching multiple `pom.xml` files.

### Neutral

- We pin plugin versions in `pluginManagement` and third-party versions
  in `dependencyManagement` on the parent.
- We use the Maven wrapper (3.9.9) so contributors don't need local Maven.

## Alternatives considered

### Alternative A: Gradle multi-project

- Pros: faster incremental builds; Kotlin DSL is more expressive.
- Cons: less common for Java SDKs; dependency hygiene tooling is thinner.
- Why rejected: ecosystem friction outweighs build-speed advantage.

### Alternative B: Per-artifact repositories

- Pros: independent release cadence; smaller CI per repo.
- Cons: cross-cutting changes span multiple PRs; version skew is easy.
- Why rejected: our modules evolve together.

### Alternative C: No BOM, consumers pick versions

- Pros: nothing to maintain.
- Cons: version skew between API, core, and engines is a support burden.
- Why rejected: BOM is a one-file cost for a large consumer benefit.

## References

- Maven BOM documentation: https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html