package com.gildedrose;

public class BackstagePassItem extends QualityUpdateItem {

  public static final int MAX_QUALITY = 50;

  public BackstagePassItem(Item item){
    super(item);
  }

  @Override
  public void gotoNextDayAndUpdateQuality() {
    --item.sellIn;
    if (item.sellIn < 0) {
      item.quality = 0;
      return;
    }
    int qualityChangeMultiplier;
    if(item.sellIn <= 5) {
      qualityChangeMultiplier = 3;
    }
    else if(item.sellIn <= 10) {
      qualityChangeMultiplier = 2;
    }
    else {
      qualityChangeMultiplier = 1;
    }
    int qualityIncreaseValue = 1 * qualityChangeMultiplier;
    item.quality = Math.min(MAX_QUALITY, item.quality + qualityIncreaseValue);
  }
}
