package com.gildedrose;

public class OrdinaryItem extends QualityUpdateItem {

  public OrdinaryItem(Item item){
    super(item);
  }

  @Override
  public void gotoNextDayAndUpdateQuality() {
    --item.sellIn;
    int qualityChangeMultiplier = item.sellIn < 0 ? 2 : 1;
    int qualityDecreaseValue = 1 * qualityChangeMultiplier;
    item.quality = Math.max(0, item.quality - qualityDecreaseValue);

  }
}
