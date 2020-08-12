package com.gildedrose;

public abstract class QualityUpdateItem {

  protected Item item;
  public QualityUpdateItem(Item item){
    this.item = item;
  }

  public abstract void gotoNextDayAndUpdateQuality();
}
