package br.com.vivareal.spotippos.dto;

public class JsonProvince {

  private Boundary boundaries;

  Boundary getBoundaries() {
    return boundaries;
  }

  void setBoundaries(Boundary boundaries) {
    this.boundaries = boundaries;
  }

  public int getUpperLeftX() {
    return boundaries.getUpperLeft().getX();
  }

  public int getUpperLeftY() {
    return boundaries.getUpperLeft().getY();
  }

  public int getBottomRightX() {
    return boundaries.getBottomRight().getX();
  }

  public int getBottomRightY() {
    return boundaries.getBottomRight().getY();
  }

  private static class Boundary {

    private Coordinates upperLeft;
    private Coordinates bottomRight;

    public Coordinates getUpperLeft() {
      return upperLeft;
    }

    public void setUpperLeft(Coordinates upperLeft) {
      this.upperLeft = upperLeft;
    }

    public Coordinates getBottomRight() {
      return bottomRight;
    }

    public void setBottomRight(Coordinates bottomRight) {
      this.bottomRight = bottomRight;
    }

    private static class Coordinates {

      private int x;
      private int y;

      public int getX() {
        return x;
      }

      public void setX(int x) {
        this.x = x;
      }

      public int getY() {
        return y;
      }

      public void setY(int y) {
        this.y = y;
      }
    }

  }

}
