package com.at.utils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static java.lang.String.format;
import static java.time.Duration.ofMillis;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.with;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.awaitility.core.ConditionFactory;
import org.awaitility.core.ConditionTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class WaitUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitUtils.class);

    private static final long MILLISECONDS = 3000L;
    public static final long TIMEOUT = Configuration.timeout;
    private static final long INTERVAL = Configuration.pollingInterval;
    private static final String LOG_MESSAGE = "{} (elapsed time {}ms, remaining time {}ms)\n";
    private static final String COMPLETE = "complete";

    private WaitUtils() {
    }

    public static void waitForPageToLoad() {
        doWaitIgnoringExceptions().until(() -> COMPLETE.equals(executeJavaScript("return document.readyState")));
    }

    public static ConditionFactory doWait() {
        return with()
                .conditionEvaluationListener(condition -> LOGGER.debug(LOG_MESSAGE, condition.getDescription(),
                        condition.getElapsedTimeInMS(), condition.getRemainingTimeInMS()))
                .atMost(TIMEOUT, TimeUnit.MILLISECONDS)
                .with().pollInterval(INTERVAL, TimeUnit.MILLISECONDS).pollInSameThread();
    }

    public static ConditionFactory doWait(final int secondsToWait) {
        return with()
                .conditionEvaluationListener(condition -> LOGGER.debug(LOG_MESSAGE, condition.getDescription(),
                        condition.getElapsedTimeInMS(), condition.getRemainingTimeInMS()))
                .atMost(secondsToWait, TimeUnit.SECONDS)
                .with().pollInterval(MILLISECONDS, TimeUnit.MILLISECONDS).pollInSameThread();
    }

    public static void doWait(final Duration duration) {
        await().pollDelay(duration).until(() -> Boolean.TRUE);
    }

    public static void doWait(final long milliseconds) {
        doWait(ofMillis(milliseconds));
    }

    public static ConditionFactory doWaitIgnoringExceptions() {
        return with()
                .conditionEvaluationListener(condition -> LOGGER.debug(LOG_MESSAGE, condition.getDescription(),
                        condition.getElapsedTimeInMS(), condition.getRemainingTimeInMS()))
                .atMost(TIMEOUT, TimeUnit.MILLISECONDS).ignoreExceptions()
                .with().pollInterval(INTERVAL, TimeUnit.MILLISECONDS).pollInSameThread();
    }

    public static void waitForDisplayedIgnoringError(SelenideElement element) {
        try {
            element.shouldBe(visible);
        } catch (ConditionTimeoutException e) {
            LOGGER.info(format("Element '%s' did not appear to be displayed within 4000 milliseconds", element));
        }
    }

}