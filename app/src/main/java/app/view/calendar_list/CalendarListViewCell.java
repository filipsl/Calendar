package app.view.calendar_list;

import com.google.common.base.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import logic.model.Calendar;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CalendarListViewCell extends ListCell<Calendar> {
    private BiConsumer<Calendar, Button> removeCalendarCallback;

    public CalendarListViewCell(BiConsumer<Calendar, Button> removeCalendarCallback) {
        this.removeCalendarCallback = removeCalendarCallback;
    }

    @Override
    public void updateItem(Calendar calendar, boolean empty) {
        super.updateItem(calendar, empty);

        if (calendar != null && !empty) {
            HBox root = new HBox(10);
            root.setAlignment(Pos.CENTER_LEFT);
            root.setPadding(new Insets(5, 5, 5, 5));

            root.getChildren().add(new Label(calendar.getName()));

            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);
            root.getChildren().add(region);

            Button removeButton = new Button("Delete");
            removeButton.setOnAction(event -> {
                removeCalendarCallback.accept(calendar, removeButton);

            });
            CheckBox checkBox = new CheckBox();
            checkBox.setOnAction(event -> {
                //todo in next iteration, when events added
                System.out.println("Calendar in use: " + checkBox.selectedProperty().getValue());
            });

            root.getChildren().addAll(removeButton, checkBox);

            setText(null);
            setGraphic(root);
        } else {
            setText(null);
            setGraphic(null);
        }
    }

}
