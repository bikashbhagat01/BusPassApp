package queryHelper;

class WhereOperation {
  public String op; // eq, lte, gte
  public String key;
  public String type;
  public String value;

  WhereOperation(String op, String key, int intVal) {
    this.op = op;
    this.key = key;
    this.type = "integer";
    this.value = intVal + "";
  }

  WhereOperation(String op, String key, String strVal) {
    this.op = op;
    this.key = key;
    this.type = "string";
    this.value = strVal + "";
  }

  public String getWhereQuery() {
    String query = "";
    String value = "";

    if (this.type == "string") {
      value = "\'" + this.value + "\'";
    } else if (this.type == "integer") {
      value = this.value + "";
    }
    String operation = "";

    if (this.op == "eq") {
      operation = "=";
    } else if (this.op == "lte") {
      operation = "<=";
    } else if (this.op == "gte") {
      operation = ">=";
    } else if (this.op == "gt") {
      operation = ">";
    } else if (this.op == "lt") {
      operation = "<";
    } else if (this.op == "neq") {
      operation = "<>";
    }

    return this.key + " " + operation + " " + value;
  }
}

