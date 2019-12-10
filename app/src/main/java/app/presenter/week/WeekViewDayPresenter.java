package app.presenter.week;

import app.presenter.DayUtils;
import app.util.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logic.model.Event;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class WeekViewDayPresenter {
    public AnchorPane dayPane;
    private static final double DAY_PX_HEIGHT = 52.0;
    private static final double DAY_PX_WIDTH = 75.0;
    private List<Event> events;

    @FXML
    private VBox hoursPane;

    private Label dayOfWeek;

    private LocalDate date;

    private String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @FXML
    public void initialize() {
        for (int i = 0; i < 25; i++) {
            if (i == 0) {
                ViewUtils.LoadedView<Object> n = ViewUtils.loadView("week/WeekViewHour.fxml");
                dayOfWeek = (Label) n.view.lookup("#hourView");
                hoursPane.getChildren().add(n.view);
                continue;
            }
            ViewUtils.LoadedView<Object> n = ViewUtils.loadView("week/WeekViewHour.fxml");
            Label label = (Label) n.view.lookup("#hourView");
            label.setText((i - 1) + ":00");
            hoursPane.getChildren().add(n.view);
        }
    }


    public void setDate(LocalDate date) {
        this.date = date;
        dayOfWeek.setText(days[date.getDayOfWeek().getValue()-1]);
        applyEvents();
    }

    private void applyEvents() {
        DayUtils.applyEvents(dayPane, date, events, DAY_PX_WIDTH, DAY_PX_HEIGHT, 0);
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
