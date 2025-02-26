# ORCID Validator Service

A microservice built with Dropwizard that validates ORCID identifiers.

## Features

- RESTful API for ORCID validation
- Built with Dropwizard framework
- Dependency injection using Dagger 2
- Java 21 support

## Technical Stack

- Java 21
- Dropwizard 2.1.1
- Dagger 2.45
- Maven for build management

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.8+

### Building the Application

```bash
mvn clean install
```

### Running the Application

```bash
mvn exec:java -Dexec.args="server config.yml"
```

### Usage

Once the application is running, you can validate ORCID identifiers through the REST API:

```bash
curl -X GET http://localhost:8080/orcid/validate/{orcid-id}
```

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               ├── api/          # REST resources
│               ├── config/       # Configuration classes
│               └── di/          # Dependency injection
├── test/
│   └── java/                    # Test classes
└── pom.xml
```

## Development

The project uses:
- Maven for dependency management and building
- JUnit for testing
- Dropwizard for the REST framework
- Dagger for dependency injection

## License

This project is licensed under the MIT License - see the LICENSE file for details
