package ru.dlts.record.enums;

public enum Reference {
  FORMED,
  PUBLISHED,
  SENT,
  PUBLISHED_AND_SENT;

  public static Reference conversely(Reference reference){
    return switch (reference){
      case FORMED ->  SENT;
      case PUBLISHED -> PUBLISHED_AND_SENT;
      default -> null;
    };
  }
}
