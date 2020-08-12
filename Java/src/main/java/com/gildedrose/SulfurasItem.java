package com.gildedrose;

public class SulfurasItem extends QualityUpdateItem {

  public SulfurasItem(Item item){
    super(item);
  }

  @Override
  public void gotoNextDayAndUpdateQuality() {
    //No changes, because never has to be sold or decrease in quality
  }
}
