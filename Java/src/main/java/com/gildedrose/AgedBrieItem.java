package com.gildedrose;

public class AgedBrieItem extends QualityUpdateItem {

  public static final int MAX_QUALITY = 50;

  public AgedBrieItem(Item item){
    super(item);
  }

  @Override
  public void gotoNextDayAndUpdateQuality() {
    --item.sellIn;
    int qualityChangeMultiplier = item.sellIn < 0 ? 2 : 1;
    int qualityIncreaseValue = 1 * qualityChangeMultiplier;
    item.quality = Math.min(MAX_QUALITY, item.quality + qualityIncreaseValue);
  }
}
