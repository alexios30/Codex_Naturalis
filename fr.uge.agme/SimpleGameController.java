����   A n   fr/uge/game/SimpleGameController  java/lang/Object <init> ()V Code
  	   LineNumberTable LocalVariableTable this "Lfr/uge/game/SimpleGameController; 
lanceLeJeu $(Lfr/umlv/zen5/ApplicationContext;)V    fr/umlv/zen5/ApplicationContext   getScreenInfo ()Lfr/umlv/zen5/ScreenInfo;    fr/umlv/zen5/ScreenInfo   getWidth ()F     	getHeight
   " ! fr/uge/game/SimpleGameView #  intitialisation
   % & ' drawcard &(Lfr/umlv/zen5/ApplicationContext;II)V context !Lfr/umlv/zen5/ApplicationContext; 
screenInfo Lfr/umlv/zen5/ScreenInfo; width F height main ([Ljava/lang/String;)V 2 fr/uge/game/RessourceCard 4 Animal 6 Empty 8 2
 1 :  ; ^(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V	 = ? > java/awt/Color @ A BLACK Ljava/awt/Color;   C D E accept :(Lfr/uge/game/RessourceCard;)Ljava/util/function/Consumer;
 G I H fr/umlv/zen5/Application J K run 0(Ljava/awt/Color;Ljava/util/function/Consumer;)V args [Ljava/lang/String; test Lfr/uge/game/RessourceCard; lambda$0 ?(Lfr/uge/game/RessourceCard;Lfr/umlv/zen5/ApplicationContext;)V
  S  
   U V W 
dessincard C(Lfr/umlv/zen5/ApplicationContext;Lfr/uge/game/RessourceCard;IIII)V 
SourceFile SimpleGameController.java BootstrapMethods
 \ ^ ] "java/lang/invoke/LambdaMetafactory _ ` metafactory �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite; [ (Ljava/lang/Object;)V b
  e P Q d  InnerClasses j %java/lang/invoke/MethodHandles$Lookup l java/lang/invoke/MethodHandles Lookup !               3     *� �    
   
                   
            #*�  L+�  E+�  F*� *2 ȸ $�    
       "  #  $  %  & " '    *    # ( )     * +    , -    . -  	 / 0     \      � 1Y33537� 9L� <+� B  � F�    
       ,  .  4          L M     N O 
 P Q     G     +� R+*2 �^ �� T�    
       /  0  1         ( )   X    Y Z     a  c f g h   
  i k m 