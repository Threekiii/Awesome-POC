from flask import Flask, jsonify
app = Flask(__name__)

@app.get("/internal")
def internal_root():
    return jsonify({"message":"ssrf ok !!"}), 200

if __name__ == "__main__":
    app.run(host="0.0.0.0",port=8000)