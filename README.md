# [Continuous Delivery for Android Using GitHub Actions](https://www.raywenderlich.com/19407406-continuous-delivery-for-android-using-github-actions)

To deliver software confidently, you need tests to ensure your changes haven’t introduced any issues. When you bring these steps together, you get *continuous delivery*.

Continuous delivery is the practice of:

1. Regularly merging code.
2. Running tests on the codebase.
3. If those tests pass, building a release version of the software.
4. Delivering the software to the customer, preferably in a staged manner.

In this chapter, you’ll learn how to use *GitHub Actions* to set up a continuous delivery pipeline that:

1. Runs tests when you are ready to send a build.
2. Generates a release build, if those tests pass.
3. Pushes the build to Firebase App Distribution to deliver to your Quality Assurance (QA) team.
4. Pushes the build to the Play Store with a rollout percentage after QA approves the changes.

The app lets you add quotes from different people, then displays those quotes in a list. It also has the option to edit the quotes.

![Screenshot of RW Quotes final UI](https://koenig-media.raywenderlich.com/uploads/2020/12/rw_quotes_app_final.jpg)

The project contains some unit and instrumentation tests too, as shown in the image below:

![RW Quotes unit and instrumentation tests](https://koenig-media.raywenderlich.com/uploads/2020/12/Screenshot-2020-12-27-at-3.05.18-AM.png)

## Using GitHub Actions

GitHub Actions is GitHub’s platform for automation workflows. A *workflow* is a sequence of jobs that can run either in series or in parallel. A job usually contains more than one step, where each step is a self-contained function. To learn more about GitHub Actions, go through the tutorial on [Continuous Integration for Android](https://www.raywenderlich.com/10562143-continuous-integration-for-android).

## Running the Tests

There are two ways you can run tests in your projects:

1. Use the *Run tests* option inside Android Studio.
2. Run the *Gradle* task for the tests from the command line.

To run tests on a remote machine, you have to go with the second option.

Run the following command from the command line to run the unit tests:

```bash
./gradlew test
```

Once you’ve done that, run the following command to run the instrumentation tests:

```bash
./gradlew connectedAndroidTest
```

Now that you know how to run the tests from the command line, add the following code to *check_and_deploy.yml*:

```yml
## 1
name: Test and deploy

## Actions that will be executed when you push code currently none
on:
  push:

## 2
jobs:
  ## 3
  unit_tests:
    runs-on: [ubuntu-latest]
    steps:
      - uses: actions/checkout@v2

      - name: Unit tests
        run: ./gradlew test
  ## 4
  android_tests:
    runs-on: [ macos-latest ]
    steps:
      - uses: actions/checkout@v2

      - name: Instrumentation Tests
        uses: reactivecircus/android-emulator-runner@v2
        with:
          api-level: 29
          script: ./gradlew connectedAndroidTest
```