package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class RuneBurn extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("RuneBurn");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/rune_burn.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;


    private final static int MAX = 3;

    public RuneBurn() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CustomTagsEnum.Rune_Tag);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // AbstractDungeon.actionManager
        // .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.BLACK,
        // p.hb.cX, p.hb.cY), 0.33F));
        // AbstractDungeon.actionManager
        // .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.PURPLE,
        // p.hb.cX, p.hb.cY), 0.33F));
        // AbstractDungeon.actionManager
        // .addToBottom(new VFXAction(p, new VerticalAuraEffect(Color.CYAN,
        // p.hb.cX, p.hb.cY), 0.0F));
        AbstractDungeon.actionManager
                .addToBottom(new VFXAction(p, new BorderLongFlashEffect(Color.MAGENTA), 0.0F, true));

        int amount = super.getRuneCount();
        if (amount > MAX)
            amount = MAX;
        AbstractDungeon.actionManager.addToTop(new GainEnergyAction(amount));
        super.useRune(amount);
    }

    public AbstractCard makeCopy() {
        return new RuneBurn();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}
