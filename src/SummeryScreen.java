
import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SummeryScreen extends Stage {
	AVlTree1<Martyer> aVlTree1 = new AVlTree1<>();
	AvlTree2<DateStack> aVlTree2 = new AvlTree2<>();
	BorderPane bPane = new BorderPane();
	VBox vBox = new VBox(10);
	VBox tableViewBox = new VBox(15);
	Button nextBT = new Button("Next");
	Button privousBT = new Button("previos");
	QueueList queueList = new QueueList();
	int counter;
	int max = 0;
	Date date;

	public SummeryScreen(Location l) {
		aVlTree1 = l.getaVlTree1();
		aVlTree2 = l.getAvlTree2();
		Label locationL = new Label(l.getLocation());
		Label AvlTree1Hieght = new Label("the hieght of tree1 is " + aVlTree1.getHeight());
		Label AvlTree2Hieght = new Label("the hieght of tree2 is " + aVlTree2.getHeight());

		locationL.setFont(new Font("Verdana", 15));
		bPane.setAlignment(locationL, Pos.CENTER);

		bPane.setTop(locationL);

		bPane.setBottom(vBox);
		bPane.setAlignment(vBox, Pos.CENTER);
		bPane.setLeft(privousBT);
		bPane.setRight(nextBT);
		bPane.setAlignment(privousBT, Pos.CENTER);

		bPane.setAlignment(nextBT, Pos.CENTER);

		TableView<Martyer> martyersTableView = new TableView<>();
		TableColumn<Martyer, String> nameCol = new TableColumn<>("name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Martyer, Integer> ageCol = new TableColumn<>("age");
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Martyer, Date> dataOfDeathCol = new TableColumn<>("date of death");
		dataOfDeathCol.setCellValueFactory(new PropertyValueFactory<>("DateOfDeath"));

		TableColumn<Martyer, Character> genderCol = new TableColumn<>("gender");
		genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

		martyersTableView.getColumns().addAll(nameCol, ageCol, dataOfDeathCol, genderCol);

		nextBT.setOnAction(e -> {
			Location location;
			// that mean you reached the end so return to the first one.
			if (StartClass.linked.search(l) == StartClass.linked.getCount() - 1)
				location = StartClass.linked.get(0);
			else
				location = StartClass.linked.get(StartClass.linked.search(l) + 1);

			this.hide();
			new SummeryScreen(location);
		});
		privousBT.setOnAction(e -> {
			Location location;
			// that mean you are at the biggening so return to the last one.
			if (StartClass.linked.search(l) == 0)
				location = StartClass.linked.get(StartClass.linked.getCount() - 1);
			else
				location = StartClass.linked.get(StartClass.linked.search(l) - 1);

			this.hide();
			new SummeryScreen(location);
		});

		TableView<Martyer> dateTableView = new TableView<>();
		TableColumn<Martyer, String> nameCol2 = new TableColumn<>("name");
		nameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Martyer, Integer> ageCol2 = new TableColumn<>("age");
		ageCol2.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Martyer, Date> dataOfDeathCol2 = new TableColumn<>("date of death");
		dataOfDeathCol2.setCellValueFactory(new PropertyValueFactory<>("DateOfDeath"));

		TableColumn<Martyer, Character> genderCol2 = new TableColumn<>("gender");
		genderCol2.setCellValueFactory(new PropertyValueFactory<>("gender"));

		dateTableView.getColumns().addAll(nameCol2, ageCol2, dataOfDeathCol2, genderCol2);
		queueList = new QueueList();
		backward(l.getAvlTree2().root);
		while (!queueList.isEmpty()) {
			Martyer martyer = queueList.dequeue();
			dateTableView.getItems().add(martyer);

		}
		queueList = new QueueList();
		InOrder(l.getaVlTree1().root);
		while (!queueList.isEmpty()) {
			Martyer martyer = queueList.dequeue();
			martyersTableView.getItems().add(martyer);

		}
		maxInorder(aVlTree2.root);
		Label maxLabel = new Label("the date that has the max number of martyers : " + date);
		maxLabel.setFont(new Font("Verdana", 15));
		Label count = new Label("the number of martyers is : " + counter);
		vBox.getChildren().addAll(AvlTree1Hieght, AvlTree2Hieght, maxLabel, count);

		tableViewBox.getChildren().addAll(martyersTableView, dateTableView);
		bPane.setCenter(tableViewBox);
		Scene scene = new Scene(bPane, 800, 800);
		this.setScene(scene);
		this.show();

	}

	void InOrder(AvlNode1<Martyer> root) {
		if (root != null) {
			counter++;
			InOrder(root.left);
			queueList.enqueue(root.element);
			InOrder(root.right);
		}

	}

	void maxInorder(AvlNode2<DateStack> root) {
		if (root != null) {
			maxInorder(root.left);
			if (max < aVlTree2.root.element.getStackList().size()) {
				max = aVlTree2.root.element.getStackList().size();
				date = aVlTree2.root.element.getDate();
			}
			maxInorder(root.right);

		}

	}

	void backward(AvlNode2<DateStack> root) {
		if (root != null) {
			backward(root.right);
			for (int i = 0; i < root.element.getStackList().size(); i++) {
			queueList.enqueue((Martyer) root.element.getStackList().pop());
			}
			backward(root.left);
		}

	}

}
