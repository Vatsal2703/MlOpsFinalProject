from sklearn.datasets import fetch_olivetti_faces
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import joblib

# 1. Load the Olivetti faces dataset
faces = fetch_olivetti_faces()
X = faces.data
y = faces.target

# 2. Split into train/test (use the SAME params as in train.py)
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.3, random_state=42, stratify=y
)

# 3. Load the trained model
clf = joblib.load("savedmodel.pth")

# 4. Predict and measure accuracy on test set
predictions = clf.predict(X_test)
acc = accuracy_score(y_test, predictions)
print(f"Test accuracy: {acc:.4f}")
