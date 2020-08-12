package com.gildedrose;

public class OrdinaryItem extends QualityUpdateItem {

  int qualityDecreaseMultiplier;
  public OrdinaryItem(Item item){
    this(item, 1);
  }

  public OrdinaryItem(Item item, int qualityDecreaseMultiplier){
    super(item);
    this.qualityDecreaseMultiplier = qualityDecreaseMultiplier;
  }

  @Override
  public void gotoNextDayAndUpdateQuality() {
    --item.sellIn;
    int sellInBaseMultiplier = item.sellIn < 0 ? 2 : 1;
    int qualityDecreaseValue = 1 * sellInBaseMultiplier * qualityDecreaseMultiplier;
    item.quality = Math.max(0, item.quality - qualityDecreaseValue);
  }
}
