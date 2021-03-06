package WarlordEmblem.cards.Kael;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.character.Kael;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class GhostWalk extends CustomCard {
    public static final String ID = WarlordEmblem.makeID("GhostWalk");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

  public static final String NAME = cardStrings.NAME;
  public static final String IMG = WarlordEmblem.assetPath("/img/cards/Kael/Kael_ghost_wander.png");
  private static final int COST = 1;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final CardType TYPE = AbstractCard.CardType.SKILL;
  private static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
  private static final CardTarget TARGET = CardTarget.SELF;
  public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;



  public GhostWalk() {

    super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    this.exhaust = true;
    this.isEthereal = true;


 }


  
  public boolean canUse(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
    if (Kael.GhostWalk_ColdDown != 0){
      this.cantUseMessage = EXTENDED_DESCRIPTION[0] + Kael.GhostWalk_ColdDown + EXTENDED_DESCRIPTION[1];
      return false;
    }
    return true;
  }

  @Override
  public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
    AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.SKY)));
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(paramAbstractPlayer, paramAbstractPlayer, new DexterityPower(paramAbstractPlayer, 2), 2));
    for (AbstractMonster abstractMonster : (AbstractDungeon.getCurrRoom()).monsters.monsters)
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster, AbstractDungeon.player, new WeakPower(abstractMonster, 2, false), 2, true));


      Kael.GhostWalk_ColdDown =  2;
  }

  @Override
  public void triggerOnEndOfPlayerTurn() {
    if (Kael.GhostWalk_ColdDown >0)
        Kael.GhostWalk_ColdDown -= 1;
      super.triggerOnEndOfPlayerTurn();
  }

  @Override
  public AbstractCard makeCopy() { return new GhostWalk(); }

  @Override
  public boolean canUpgrade() { return false; }


  @Override
  public void upgrade() {
      if (!this.upgraded) {
      upgradeName();
      upgradeBaseCost(0);
  }  }

}

