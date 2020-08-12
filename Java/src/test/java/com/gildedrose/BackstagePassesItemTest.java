package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackstagePassesItemTest {

  final static int BASIC_QUALITY_CHANGE_MULTIPLIER                       = 1;
  final static int QUALITY_INCREASE_MULTIPLIER_WITH_10_OR_LESS_DAYS_LEFT = 2;
  final static int QUALITY_INCREASE_MULTIPLIER_WITH_5_OR_LESS_DAYS_LEFT = 3;

  final static int MAX_QUALITY                     = 50;

  private final static String backstagePassesItemName = "Backstage passes to a TAFKAL80ETC concert";

  @Test
  @DisplayName( "quality increase with more than 10 days left before concert" )
  void qualityWithOver10DaysLeftBeforeTest() {
    int initialQuality = 10;
    int initialSellIn = 15;
    Item item = new Item(backstagePassesItemName, initialSellIn, initialQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 1, app.items[0].sellIn);
    testQuality(initialQuality, 1, BASIC_QUALITY_CHANGE_MULTIPLIER, app.items[0]);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 2, app.items[0].sellIn);
    testQuality(initialQuality, 2, BASIC_QUALITY_CHANGE_MULTIPLIER, app.items[0]);
  }

  @Test
  @DisplayName( "quality increase with 10 days or less left" )
  void qualityWith10DaysOrLessLeftBeforeTest() {
    int initialQuality = 10;
    int initialSellIn = 10;
    Item item = new Item(backstagePassesItemName, initialSellIn, initialQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 1, app.items[0].sellIn);
    testQuality(initialQuality, 1, QUALITY_INCREASE_MULTIPLIER_WITH_10_OR_LESS_DAYS_LEFT, app.items[0]);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 2, app.items[0].sellIn);
    testQuality(initialQuality, 2, QUALITY_INCREASE_MULTIPLIER_WITH_10_OR_LESS_DAYS_LEFT, app.items[0]);
  }

  @Test
  @DisplayName( "quality increase with 5 days or less left" )
  void qualityWith5DaysOrLessLeftBeforeTest() {
    int initialQuality = 10;
    int initialSellIn = 5;
    Item item = new Item(backstagePassesItemName, initialSellIn, initialQuality);
    GildedRose app = new GildedRose(item);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 1, app.items[0].sellIn);
    testQuality(initialQuality, 1, QUALITY_INCREASE_MULTIPLIER_WITH_5_OR_LESS_DAYS_LEFT, app.items[0]);

    app.gotoNextDayAndUpdateQuality();
    assertEquals(initialSellIn - 2, app.items[0].sellIn);
    testQuality(initialQuality, 2, QUALITY_INCREASE_MULTIPLIER_WITH_5_OR_LESS_DAYS_LEFT, app.items[0]);
  }

  @Test
  @DisplayName( "the quality should never go above " + MAX_QUALITY)
  void maximumQualityTest() {
    Item item = new Item(backstagePassesItemName, 2, MAX_QUALITY - 1 );
    GildedRose app = new GildedRose(item);
    app.gotoNextDayAndUpdateQuality();
    assertEquals(MAX_QUALITY, item.quality);
    app.gotoNextDayAndUpdateQuality();
    assertEquals(MAX_QUALITY, item.quality);
  }

  void testQuality(int initialQuality, int dayIncrement, int qualityDegradationMultiplier, Item item){
    assertEquals(initialQuality + dayIncrement * qualityDegradationMultiplier, item.quality);
  }

  @Test
  @DisplayName( "the quality drops to 0 after concert" )
  void afterConcertEndQualityTest() {
    Item item = new Item(backstagePassesItemName, 0, 20);
    GildedRose app = new GildedRose(item);
    app.gotoNextDayAndUpdateQuality();
    assertEquals(0, item.quality);
  }
}
