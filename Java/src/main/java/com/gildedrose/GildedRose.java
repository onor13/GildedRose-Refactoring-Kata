package com.gildedrose;

class GildedRose {
    Item[] items;

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_TAFKAL80ETC = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public GildedRose(Item item) {
        this.items = new Item[] { item };
    }

    public void gotoNextDayAndUpdateQuality() {
        for (int i = 0; i < items.length; i++) {
           QualityUpdateItem item = ItemFactory.getTimedItem(items[i]);
           item.gotoNextDayAndUpdateQuality();
        }
    }
}