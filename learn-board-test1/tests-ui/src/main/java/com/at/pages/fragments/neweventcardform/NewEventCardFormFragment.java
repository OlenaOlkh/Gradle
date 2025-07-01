package com.at.pages.fragments.neweventcardform;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import com.at.pages.fragments.AbstractFragment;

public class NewEventCardFormFragment extends AbstractFragment {

    private final String addTitleField = "input[name='add-title']";
    private final String useTeamsMeetingCheckbox = "input[name='use-teams-meeting']";
    private final String startDateInput = "input[name='date-start']";
    private final String startTimeInput = "input[name='time-start']";
    private final String endDateInput = "input[name='date-end']";
    private final String endTimeInput = "input[name='time-end']";
    private final String lectureDetailsTab = ".//button[text()='Lecture Details']";
    private final String schedulingAssistantTab = ".//button[text()='Scheduling Assistant']";

    private final String addParticipantsPlaceholder = "div#add-participants-field .selected-with-dropdown";
    private final String addParticipantsDropdown = "div#add-participants-field .dropdown-input";

    private final String saveDraftButton = "button.footer__button--save-as-draft";
    private final String createButton = "button.footer_button--save";

    private final String lectureDetailsRootElementLocator = "div.event-details-section";
    private final String schedulingAssistantRootElementLocator = ".repeat-details";

    private final SelenideElement rootElement =
            $x("//form[@class='event-form-card-form']/ancestor::div[contains(@class, 'ReactModal__Content')]");

    public NewEventCardFormFragment() {
        setRootElement(rootElement);
    }

    public LectureDetailsFragment getLectureDetailsFragment() {
        return getFragmentFactory().createFragment(getRootElement().$(lectureDetailsRootElementLocator),
                LectureDetailsFragment.class);
    }

    public SchedulingAssistantFragment getSchedulingFragment() {
        return getFragmentFactory().createFragment(getRootElement().$(schedulingAssistantRootElementLocator),
                SchedulingAssistantFragment.class);
    }

    public void enterStartDate(String startDate) {
//        date pattern YYYY-MM-DD
        getStartDateInput().setValue(startDate);
    }

    public void enterStartTime(String startTime) {
//        time pattern MM:HH
        getStartDateInput().setValue(startTime);
    }

    public SelenideElement getAddTitleField() {
        return getRootElement().$(addTitleField);
    }

    public SelenideElement getUseTeamsMeetingCheckbox() {
        return getRootElement().$(useTeamsMeetingCheckbox);
    }

    public SelenideElement getStartDateInput() {
        return getRootElement().$(startDateInput);
    }

    public SelenideElement getStartTimeInput() {
        return getRootElement().$(startTimeInput);
    }

    public SelenideElement getEndDateInput() {
        return getRootElement().$(endDateInput);
    }

    public SelenideElement getEndTimeInput() {
        return getRootElement().$(endTimeInput);
    }

    public SelenideElement getLectureDetailsTab() {
        return getRootElement().$x(lectureDetailsTab);
    }

    public SelenideElement getSchedulingAssistantTab() {
        return getRootElement().$x(schedulingAssistantTab);
    }

    public SelenideElement getAddParticipantsPlaceholder() {
        return getRootElement().$(addParticipantsPlaceholder);
    }

    public SelenideElement getAddParticipantsDropdown() {
        return getRootElement().$(addParticipantsDropdown);
    }

    public SelenideElement getSaveDraftButton() {
        return getRootElement().$(saveDraftButton);
    }

    public SelenideElement getCreateButton() {
        return getRootElement().$(createButton);
    }

}
