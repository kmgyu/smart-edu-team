# Simple Bulletin Board Example

A simple bulletin board application to demonstrate basic web development concepts.

I could have written it in Korean, but we just wrote it in English. cuz it looks cool LOL(but it didn't... ahhh)

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Workspace](#workspace)

## Introduction
This is a simple bulletin board application designed as a tutorial to help beginners understand the basics of web development. The application allows users to create, read, update, and delete (CRUD) posts. 
The application runs on a Web Application Server (WAS) using Tomcat, with both the frontend and backend operating on the WAS.
* the main branch refactoring the DBMS(mongoDB -> mysql) is completed! hooray!

## Features
- Create new posts
- View a list of all posts
- Edit existing posts
- Delete posts
- Comments
- Comments to Comment
- Like to Posts
- Like to Comments

## Technologies Used
- **Frontend**: HTML, CSS, Thymeleaf, JS(New! Finally JS...)
- **Backend**: Java 17 SpringBoot
- **Database**: MySQL

## Installation
0. Required:
  - Java 17(development language is 19 but 17 will be stable... i don't know why)
  - IntelliJ (This Project made by IntelliJ so may not work in other IDE)
  - MySQL Workbench(It's for Windows OS, if you are Linux OS, just installing a mysql will be easier...)
  
1. Clone the repository:
    ```sh
    mkdir simple-bulletin-board
    git clone https://github.com/kmgyu/smart-edu-team.git
    cd simple-bulletin-board
    ```

2. Build gradle:
    ```sh
    gradle build
    ```
    or put on your cursor to build.gradle and press right button and build...

3. Set up the database:
    - Ensure MongoDB is installed.
    - Check that MongoDB is working properly.

4. Start the server:
    ```sh
    gradle run
    ```
    or use run button

## Usage
1. Open your web browser and navigate to `http://localhost:8080/`.
2. Use the interface to create, view, edit, and delete posts.

## WorkSpace
Check our workspace. We hope this will help you.

[the notion workspace!](https://radial-woodwind-25c.notion.site/4263a20cfe074c3dbc36fd003dd085a5?v=ec79e0e5846d4a13b012d5ddb6fd6a8c)
