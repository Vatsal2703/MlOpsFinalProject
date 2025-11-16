from sklearn.datasets import fetch_olivetti_faces
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
import joblib

# 1. Load the Olivetti faces dataset
faces = fetch_olivetti_faces()
X = faces.data  # Features (images flattened into vectors)
y = faces.target  # Labels (person IDs)

# 2. Split into train/test (70/30)
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.3, random_state=42, stratify=y
)

# 3. Train the Decision Tree Classifier
clf = DecisionTreeClassifier(random_state=42)
clf.fit(X_train, y_train)

# 4. Optionally, check training accuracy
train_acc = accuracy_score(y_train, clf.predict(X_train))
print(f"Training accuracy: {train_acc:.4f}")

# 5. Save the trained model to disk
joblib.dump(clf, "savedmodel.pth")
print("Model saved as 'savedmodel.pth'")
