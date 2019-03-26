pragma solidity ^0.5.6;


contract split{
    address[] farm = [0xc451Ecb6681467564876D428F76Ff07674c1C56a, 0x55bDF489902F4906e7bbDa71272CfD0bf8B5c245, 0x71F084cf96ef66659792194c3d5e33045C8Cb9Ca, 0x22369B3a14682f6167057BBC279dfEf5885A1900, 0xDF38069C059e9B31B354e7a73E84eC7bA92cc575  ];
    uint totalReceived = 0;
    mapping (address => uint) withdrawnAmounts;
    
    constructor()public payable{
        updateTotalReceived();
    }
    
    function updateTotalReceived() internal{
        totalReceived += msg.value;
    }
    
    modifier canWithdraw(){
        bool contains = false;
        for(uint i =0;i<farm.length; i++){
            if(farm[i] == msg.sender){
                contains = true;
            }
        }
        require (contains);
        _;
    }
    
    function withdraw() public canWithdraw{
        uint amountAllocated = totalReceived/farm.length;
        uint amountWithdrawn = withdrawnAmounts[msg.sender];
        uint amount = amountAllocated - amountWithdrawn;
        withdrawnAmounts[msg.sender] = amountWithdrawn + amount;
        
        if(amount>0){
            msg.sender.transfer(amount);
        }
        
    }
}