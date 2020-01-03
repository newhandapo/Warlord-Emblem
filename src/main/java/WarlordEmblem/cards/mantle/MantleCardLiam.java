package WarlordEmblem.cards.mantle;

import java.util.ArrayList;
import java.util.Collections;

import WarlordEmblem.WarlordEmblem;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MantleCardLiam extends AbstractMantleCard {
    public static final String ID = WarlordEmblem.makeID("MantleCardLiam");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Mantle/mantle_card_liam.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public MantleCardLiam() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // count cost = 1 card & remove it
        CardGroup group = p.drawPile;
        ArrayList<AbstractCard> cardList = group.group;
        int count = 0;
        for (int i = cardList.size() - 1; i >= 0; i--) {
            AbstractCard card = cardList.get(i);
            if (card.cost == 1) {
                count++;
                cardList.remove(i);
            }
        }

        group = MantleCardMarkzar.getEachRare(AbstractDungeon.player.chosenClass);
        cardList = group.group;
        for (int i = 0; i < count; i++) {
            Collections.shuffle(cardList);
            AbstractCard card = cardList.get(0);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(card,
                    1, true, false,false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
        }
    }

    public AbstractCard makeCopy() {
        return new MantleCardLiam();
    }
}
