package com.gildedrose;

public class ItemFactory {

  public static QualityUpdateItem getTimedItem(Item item){
    if(item == null){
      return null;
    }
    QualityUpdateItem timedItem;
    if (item.name.equals(GildedRose.AGED_BRIE)) {
      timedItem = new AgedBrieItem(item);
    }
    else if (item.name.equals(GildedRose.SULFURAS)) {
      timedItem = new SulfurasItem(item);
    }
    else if(item.name.equals(GildedRose.BACKSTAGE_PASSES_TO_TAFKAL80ETC)) {
      timedItem = new BackstagePassItem(item);
    }
    else{
      timedItem = new OrdinaryItem(item);
    }
    return timedItem;
  }
}
