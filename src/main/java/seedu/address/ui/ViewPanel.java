package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * A UI component that displays the full profile of a selected contact.
 */
public class ViewPanel extends UiPart<Region> {
    private static final String FXML = "ViewPanel.fxml";

    @FXML
    private Label name;
    @FXML
    private VBox gamesList;

    /**
     * Constructor for ViewPanel
     */
    public ViewPanel() {
        super(FXML);
        // Default empty state before any command is run
        name.setText("Select a profile to view.");
    }

    /**
     * Updates the UI to display the details of the given {@code Person}.
     */
    public void setPerson(Person person) {
        if (person == null) {
            return;
        }

        // 1. Set the Name
        name.setText(person.getName().fullName);

        // 2. Clear out any previous games
        gamesList.getChildren().clear();

        // 3. Populate the Games and Aliases
        if (person.getGames().isEmpty()) {
            Label emptyLabel = new Label("Games: None added yet.");
            emptyLabel.getStyleClass().add("cell_small_label");
            gamesList.getChildren().add(emptyLabel);
        } else {
            Label gamesTitle = new Label("Games:");
            gamesTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 14px;");
            gamesList.getChildren().add(gamesTitle);

            person.getGames().forEach(game -> {
                StringBuilder gameText = new StringBuilder(" • " + game.gameName);
                if (!game.getAliases().isEmpty()) {
                    gameText.append(" [Aliases: ");
                    game.getAliases().forEach(alias -> gameText.append(alias).append(", "));
                    gameText.setLength(gameText.length() - 2); // Remove trailing comma and space
                    gameText.append("]");
                }
                Label gameLabel = new Label(gameText.toString());
                gameLabel.getStyleClass().add("cell_small_label"); // Uses standard AB3 styling
                gamesList.getChildren().add(gameLabel);
            });
        }
    }
}
