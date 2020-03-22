package queryHelper;

class InsertOperation {
  public String key;
  public String value;
  public String type;

  InsertOperation(String key, String value) {
    this.type = "string";
    this.key = key;
    this.value = value;
  }

  InsertOperation(String key, int value) {
    this.type = "integer";
    this.key = key;
    this.value = value + "";
  }

  String getUpdateQuery() {
    String query = "";
    String value = "";

    if (this.type == "string") {
      value = "\'" + this.value + "\'";
    } else if (this.type == "integer") {
      value = this.value + "";
    }

    return this.key + " = " + value;
  }
}
