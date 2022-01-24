// SPDX-License-Identifier: MIT
pragma solidity ^0.8.7;

/*
    Murat Celik
    21827263 Computer Science
    20/12/21
    BBM 443 Project-Fall 2021 
    Instructor: Dr. Adnan Özsoy

*/
contract TaxiProject{
    /*
    State Variables :
        Participants: maximum of 9, each participant identified with an address and has a balance 
        Taxi Driver: 1 taxi driver, salary  balance
        Car Dealer: An identity to buy/sell car, also handles maintenance and tax 
        Contract balance: Current total money in the contract that has not been distributed 
        Fixed expenses: Every 6 months car needs to go to Car Dealer for maintenance and taxes need (10 Ether for every 6 months)
        Participation fee: An amount that participants needs to pay for entering the taxi business (100 Ether)
        Owned Car: identified with a 32 digit number, CarID 
        Proposed Car: Car proposal proposed by the CarDealer
        Proposed Repurchase: Car repurchase proposal proposed by the CarDealer
        Vote Array : holds the votes in the voting, open vote 
        Time Handle : timeOfSalary, timeOfExpenses, timeOfDividend
    */

    struct Participant{
        address addOfParticipant;
        uint balanceOfParticipant;
    }
    Participant[] participants;

    
    struct TaxiDriver{
        address addressOfDriver;
        uint salaryOfDriver;
        uint balanceOfDriver;
    }
    TaxiDriver taxiDriver;

    struct ProposedCar{
        uint id;
        uint price;
        uint validTime;
        int appState;
    }
    ProposedCar proposedCar;

    struct ProposedRepurchase{
        uint256 idRe;
        uint priceRe;
        uint validTimeRe;
        int stateRe;
    }
    ProposedRepurchase proposedRepurchase;


    address carDealer;
    address ownerContract;

    uint contractBalance;
    uint fixedExpenses;
    uint participationFee;
    uint128 ownedCarId;


    //Modifiers
    modifier onlyCarDealer(){
        require(msg.sender == carDealer , "You are not Car Dealer!");
        _;
    } 
    modifier onlyParticipants(){
        require(isExist(msg.sender) , "You are not any of Participant!");
        _;
    } 
    modifier onlyOwnerContract(){
        require(msg.sender == ownerContract , "You are not Owner of Contract!");
        _;
    }
    modifier onlyTaxiDriver(){
        require(msg.sender == taxiDriver.addressOfDriver , "You are not Taxi Driver!");
        _;
    } 

    // Helping function

    function isExist( address _add) public view returns(bool) {
        bool isexist = false;
        for (uint i=0; i < participants.length; i++) {
            if (_add == participants[i].addOfParticipant) {
                isexist = true;
            }
        }
        return isexist;
    }


    // 20 function

    // 1) Constructor
    constructor() {
        ownerContract = 0x5B38Da6a701c568545dCfcB03FcB875f56beddC4;  // owner of contract
        carDealer = 0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2;   // An identity to buy/sell car
        taxiDriver = TaxiDriver(0x4B20993Bc481177ec7E8f571ceCaE8A9e22C02db,5,0); // create object of Taxi Driver
        // time initialize
        timeOfSalary = block.timestamp; // driver start work, after 30 days, he/she will got salary
        timeOfExpenses = block.timestamp; // Expenses will be paid after 6 months 
        timeOfDividend = block.timestamp; // calculates the total profit every 6 months
        timeOfGETDividend = block.timestamp; // calculates the total profit and send every 6 months


        contractBalance =  0 ether; // Current total money in the contract that has not been distributed
        fixedExpenses = 10 ether; // maintenance and tax fot evry 6 mounts fee
        participationFee =  100  ether; // An amount that participants needs to pay for entering the taxi business
        ownedCarId = 68002913585123093762532287468801;  //  identified with a 32 digit number

    }
    // 2) Join
    function Join() public payable{     
        uint val = msg.value ;       
        bool doesListContainElement = isExist(msg.sender);
        if ( !doesListContainElement ){ // if this addres not in list, go on
            if(val >= participationFee){ // check current participant is rich or poor
                if(participants.length <9){ // chechk limit of participants
                    contractBalance += participationFee;  // add money to balance
                    participants.push(Participant(msg.sender,val - participationFee));
		    payable(ownerContract).transfer(participationFee);              
              
                    }
            }
        }
    }

    // 3) CarProposeToBusiness
    function CarProposeToBusiness(uint _id, uint _price, uint _validTime) public onlyCarDealer{
        proposedCar = ProposedCar(_id, _price, _validTime, 0);
    }
    function returnLeng() public view returns(uint){
        return participants.length;
    }
    
    // 4) ApprovePurchaseCar
    mapping(address => int) public votePurchaseCar; // keeps what each member voted  for Purchase Car
    uint voteLength = 0; // keeps how many member voted positive
    function ApprovePurchaseCar() public onlyParticipants{
        if (votePurchaseCar[msg.sender] == 0){ // prevents someone who voted positive from voting again 
            voteLength+=1;
            votePurchaseCar[msg.sender] = 1;
        }
        if (participants.length/2 < voteLength ){ //  majority of participants approve
            PurchaseCar(); // calls PurschaseCar function by owner contract
            for (uint i=0; i < participants.length; i++) { votePurchaseCar[participants[i].addOfParticipant] = 0;}
            voteLength = 0;
        }
    }

    // 5) PurchaseCar
    function PurchaseCar() public payable {
        //  majority of participants approve, if function call outside ApprovePurchaseCar function, chechk again majority
        if (participants.length/2 < voteLength ){ 
            if (proposedCar.validTime >= block.timestamp ){ // if the offer valid time is not passed yet
                payable(carDealer).transfer(proposedCar.price); // Sends the CarDealer the price of the proposed car
                contractBalance -= proposedCar.price; 
            }
        }
    }
    
    // 6) RepurchaseCarPropose
    function RepurchaseCarPropose(uint _id, uint _price, uint _validTime) public onlyCarDealer{
        proposedRepurchase = ProposedRepurchase(_id, _price, _validTime, 0);
    }

    // 7) ApproveSellProposal
    mapping(address => int) public voteRePurchaseCar; // keeps what each member voted  for Repurchase
    uint voteReLength = 0; // keeps how many member voted positive
    function ApproveSellProposal() public onlyParticipants{
        if (voteRePurchaseCar[msg.sender] == 0){ // prevents someone who voted positive from voting again 
            voteReLength+=1;
            voteRePurchaseCar[msg.sender] = 1;
        }
        if (participants.length/2 < voteReLength ){ //  majority of participants approve
            Repurchasecar();  // calls Repurchasecar function
            for (uint i=0; i < participants.length; i++) { voteRePurchaseCar[participants[i].addOfParticipant] = 0;}
            voteReLength = 0;
        }
    }

    // 8) Repurchasecar
    function Repurchasecar() public payable {
        if (participants.length/2 < voteReLength ){ //  majority of participants approve
            if (proposedCar.validTime >= block.timestamp){
                uint priceOfReProposedCar = msg.value;
                payable(ownerContract).transfer(priceOfReProposedCar);
                contractBalance += priceOfReProposedCar;
            }
        }
    }

    // 9) ProposeDriver
    TaxiDriver proposeDriver ; // proposes himself/herself as driver and sets his/her address, and expected salary
    function ProposeDriver(address _addOfDriver,uint _salaryOfDriver) public {
        proposeDriver.addressOfDriver = _addOfDriver; 
        proposeDriver.salaryOfDriver = _salaryOfDriver;
    }

    // 10) ApproveDriver
    mapping(address => int) public voteDriverApprove;
    uint voteLengthDriverApprove = 0 ;
    function ApproveDriver() public onlyParticipants{
        if (voteDriverApprove[msg.sender] == 0){ // one vote for each member
            voteLengthDriverApprove+=1;
            voteDriverApprove[msg.sender] = 1;
        }
        if (participants.length/2 < voteLengthDriverApprove ){ // driver was accepted
            SetDriver();    
            for (uint i=0; i < participants.length; i++) { voteDriverApprove[participants[i].addOfParticipant] = 0;}
            voteLengthDriverApprove = 0;
        }
    }

    // 11) SetDriver
    function SetDriver() private {
        if (participants.length/2 < voteLengthDriverApprove ){ // driver was accepted, if call outside the ApproveDriver function
            taxiDriver.addressOfDriver = proposeDriver.addressOfDriver; // set driver
            taxiDriver.salaryOfDriver = proposeDriver.salaryOfDriver;
            delete proposeDriver;// Clears proposed driver info 
            timeOfSalary = block.timestamp; // driver start work, after 30 days he/she will got salary
        }
    }

    // 12) ProposeFireDriver
    mapping(address => int) public voteDriverFire;
    uint voteLengthDriverFire = 0 ;
    function ProposeFireDriver() public onlyParticipants{
        if (voteDriverFire[msg.sender] == 0){ // one vote for each member
            voteLengthDriverFire+=1;
            voteDriverFire[msg.sender] = 1;
        }
        if (participants.length/2 < voteLengthDriverFire ){ // majority of participants approve
            FireDriver();
            for (uint i=0; i < participants.length; i++) { voteDriverFire[participants[i].addOfParticipant] = 0;}
            voteLengthDriverFire = 0;
        }
    }
    
    // 13) FireDriver
    function FireDriver() public payable{
        // majority of participants approve or called by driver
        if (msg.sender == taxiDriver.addressOfDriver || participants.length/2 < voteLengthDriverFire ){ 
            payable(taxiDriver.addressOfDriver).transfer(taxiDriver.balanceOfDriver); // Sends the owner of the contract the charge
            delete taxiDriver;// Clears  driver info 
        }
    }

    // 14) LeaveJob
    function LeaveJob() public onlyTaxiDriver{ // Only Driver can call this function
        FireDriver(); // calls FireDriver function
    }
    

    // 15) GetCharge
    function GetCharge() public payable {
        uint priceOfCharge = msg.value / 1e18;
        payable(ownerContract).transfer(priceOfCharge); // Sends the owner of the contract the charge
        contractBalance += priceOfCharge;
    }

    // 16) GetSalary
    uint timeOfSalary;
    function GetSalary() public payable onlyTaxiDriver{
        if (timeOfSalary + 30 days < block.timestamp){  // every month
            contractBalance -= taxiDriver.salaryOfDriver;
            taxiDriver.balanceOfDriver += taxiDriver.salaryOfDriver;
            payable(taxiDriver.addressOfDriver).transfer(taxiDriver.salaryOfDriver); // Sends the owner of the contract the charge
            taxiDriver.balanceOfDriver +=  taxiDriver.salaryOfDriver;
            timeOfSalary = block.timestamp;
        }
    }

    // 17) CarExpenses
    uint timeOfExpenses;
    function CarExpenses() public payable onlyParticipants{
        if (timeOfExpenses + 180 days < block.timestamp){ // 6 months
            payable(carDealer).transfer(fixedExpenses); // Sends the owner of the contract the charge
            contractBalance -= fixedExpenses;
        }
    }
    
    // 18) PayDividend
    uint timeOfDividend;
    function PayDividend() public onlyParticipants{
        if (timeOfDividend + 180 days < block.timestamp){ // 6 months
            // after expenses and Driver salaries
            CarExpenses();
            if (timeOfSalary + 30 days < block.timestamp){  // every month
                contractBalance -= taxiDriver.salaryOfDriver;
                taxiDriver.balanceOfDriver += taxiDriver.salaryOfDriver;
                payable(taxiDriver.addressOfDriver).transfer(taxiDriver.salaryOfDriver); // Sends the owner of the contract the charge
                taxiDriver.balanceOfDriver +=  taxiDriver.salaryOfDriver;
                timeOfSalary = block.timestamp;
            }

            uint perCapitaSalary = contractBalance / participants.length;
            for (uint i=0; i < participants.length; i++){
                participants[i].balanceOfParticipant += perCapitaSalary;
            }
            contractBalance = 0;
        }
    }

    // 19) GetDividend
    uint timeOfGETDividend;
    function GetDividend() public payable onlyParticipants{
        if (timeOfGETDividend + 180 days < block.timestamp){ // 6 months
            for (uint i=0; i < participants.length; i++){
                payable(participants[i].addOfParticipant).transfer(participants[i].balanceOfParticipant); // Sends the owner of the contract the charge
                participants[i].balanceOfParticipant = 0;
            }
        }
    }
    
    // 20) FallBack
    mapping (address => uint) balance;
    fallback() external payable{
        balance[msg.sender] += msg.value;
    }
    receive() external payable {
        balance[msg.sender] += msg.value;
    }

}