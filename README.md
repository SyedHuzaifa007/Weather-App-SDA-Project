# WeatherApp

## Overview

WeatherApp is a comprehensive weather application designed to provide users with accurate weather forecasts and essential information for their chosen locations. Built upon robust software design principles, WeatherApp offers a range of features to meet your weather tracking needs. From basic weather conditions to detailed forecasts and air quality data, WeatherApp has got you covered.

## Features

- **Multiple Location Support**: Add and track weather conditions for multiple locations using longitude and latitude coordinates or city/country names.
- **Current Weather Conditions**: Get real-time updates on current weather conditions including temperature, humidity, and wind speed.
- **Detailed Information**: View additional information such as "Feels like" temperature, minimum and maximum temperature, sunrise and sunset times.
- **5-Day Weather Forecast**: Plan ahead with a 5-day weather forecast to stay prepared for upcoming changes.
- **Timestamped Records**: Keep track of when weather data was last accessed for each location.
- **Cache Management**: Utilize database caching to reduce API calls and improve application performance.
- **Notification Alerts**: Receive notifications for poor weather conditions and air quality to stay informed and safe.
- **Air Pollution Data**: Access air pollution data and monitor polluting gases in your area.

## Design Flexibility

WeatherApp is designed with flexibility in mind, offering multiple user interface options and storage methods:

- **User Interfaces**:
  - Swing JAVA UI
  - Console-based UI

- **Storage Methods**:
  - SQL Server
  - Text Files

Each UI and storage method is implemented independently, allowing for easy customization and scalability.

## SOLID Principles

WeatherApp adheres to the SOLID principles of object-oriented design:

- **Single Responsibility Principle (SRP)**: Each class in WeatherApp has a single responsibility, such as handling UI interactions, fetching weather data, or managing cache. This ensures that each class is focused and easier to maintain.

- **Open/Closed Principle (OCP)**: WeatherApp is designed to be open for extension but closed for modification. New features can be added through inheritance and composition without altering existing code.

- **Liskov Substitution Principle (LSP)**: WeatherApp ensures that derived classes can be substituted for their base classes without affecting the correctness of the program. This allows for easy integration of new UI components or storage methods.

- **Interface Segregation Principle (ISP)**: WeatherApp interfaces are segregated to ensure that clients only depend on the methods they use. For example, the UI classes only depend on the necessary methods from the weather data provider interface.

- **Dependency Inversion Principle (DIP)**: WeatherApp relies on abstractions rather than concrete implementations. High-level modules such as UI components depend on abstractions like interfaces, rather than concrete classes. This promotes flexibility and easier testing.

## Efficient Software Design

WeatherApp emphasizes efficient software design by:

- **Reducing API Calls**: Utilizing cache management techniques to minimize the number of API calls and improve application performance.
- **Modular Architecture**: Breaking down the application into smaller, cohesive modules with clear responsibilities, allowing for easier maintenance and scalability.
- **Scalable Storage Solutions**: Offering multiple storage methods and hosting the database on a remote server to ensure scalability and reliability.
- **Flexible User Interfaces**: Providing multiple UI options to cater to different user preferences and environments.

## Technology Stack

- **Frontend**: Swing, Console Based
- **Backend**: Java
- **Database**: Txt Files, SQL Server (hosted on a remote server)

## Usage

1. Clone the repository to your local machine.
2. Choose your preferred UI and storage method by configuring the application settings.
3. Build and run the application to start tracking the weather for your desired locations.

Let WeatherApp keep you informed about the weather conditions wherever you are! Happy tracking! 🌤️🌦️🌧️🌩️
