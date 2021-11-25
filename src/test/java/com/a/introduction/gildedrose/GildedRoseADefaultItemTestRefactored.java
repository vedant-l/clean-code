package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTestRefactored {

    public static final int NOT_EXPIRED_SELL_IN = 15;
    public static final int DEFAULT_QUALITY = 3;
    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    public static final int EXPIRED_SELL_IN = -1;
    public static final int EXPIRED_ITEM_QUALITY = 3;

    @Test
    public void unexpiredDefaultItem_QualityDecreasesBy1() {

        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 1);
        assertItem(expectedItem, app.items[0]);
    }

    @Test
    public void expiredDefaultItem_QualityDecreasesBy2() {

        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELL_IN, EXPIRED_ITEM_QUALITY);

        app.updateQuality();

        Item expectedItem = new Item(DEFAULT_ITEM, EXPIRED_SELL_IN - 1, EXPIRED_ITEM_QUALITY - 2);
        assertItem(expectedItem, app.items[0]);
    }

    private GildedRose createGildedRoseWithOneItem(String name, int sellIn, int quality) {
        Item item = new Item(name, sellIn, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }

    private void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }
}