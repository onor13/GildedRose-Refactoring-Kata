package com.gildedrose;

public class OrdinaryItemTest extends BasicItemTest {

  @Override
  protected int getPositiveSellInQualityChangeMultiplier() {
    return 1;
  }

  @Override
  protected String getItemName() {
    return "Ordinary item";
  }
}
